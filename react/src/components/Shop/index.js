import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import { Product } from './Product'
import { Cart } from '../Cart'
import Button from '@material-ui/core/Button'
import { CheckOut } from '../Cart/Checkout'
import { CheckStatus } from './CheckStatus'
import { Customize } from './Customize'

const useStyles = makeStyles(theme => ({
  container: {
    margin: '30px auto',
    justifyContent: 'center',
    textAlign: 'center'
  },
  gridRoot: {
    flexGrow: 1
  },
  wrapper: {
    display: 'grid',
    gridTemplateColumns: 'repeat(5, 1fr)',
    gap: '20px'
  },
  button: {
    backgroundColor: '#66ccff'
  }
}))

const Shop = () => {
  // retrieve all from database
  const [cartList, setCartList] = useState([])
  const [productList, setProductList] = useState([])
  const [open, setOpen] = React.useState(false)
  const [openCheckout, setOpenCheckout] = React.useState(false)
  const [openCheckStatus, setOpenCheckStatus] = React.useState(false)
  const [openCustomize, setOpenCustomize] = React.useState(false)

  const handleAdd = product => {
    for (let i = 0; i < cartList.length; i++) {
      if (cartList[i]['product']['productid'] === product['productid']) {
        let temp = [...cartList]
        temp[i]['count'] = cartList.splice(i, 1)[0]['count'] + 1
        setCartList([...temp])
        return
      }
    }
    setCartList([{ product: product, count: 1 }, ...cartList])
  }

  const handleRemove = (item, mode) => {
    let index = cartList.indexOf(item['product'])
    let temp = [...cartList]
    if (mode === 'all') {
      temp.splice(index, 1)
      setCartList([...temp])
    } else {
      let removeItem = temp.splice(index, 1)
      if (removeItem[0]['count'] - 1 === 0) {
        setCartList([...temp])
      } else {
        removeItem[0]['count'] -= 1
        temp = [...temp, removeItem[0]]
        setCartList([...temp])
      }
    }
  }

  const handleIncrement = cartItem => {
    handleAdd(cartItem['product'])
  }

  const getProducts = () => {
    request
      .get(BuildPath('/customer/allProduct'))
      .set('Authorization', localStorage.getItem('Authorization'))
      .set('Accept', 'application/json')
      .then(res => {
        if (res.status === 200) {
          var prolist = JSON.stringify(res.body)
          if (JSON.stringify(productList) !== prolist) {
            setProductList(res.body)
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
  }

  useEffect(() => {
    getProducts()
  })

  const classes = useStyles()

  return (
    <>
      <Grid container className={classes.container}>
        <Grid item md={4}>
          <Button
            onClick={() => setOpenCustomize(true)}
            className={classes.button}
          >
            Customize Order
          </Button>
        </Grid>
        <Grid item md={4}>
          <Button onClick={() => setOpen(true)} className={classes.button}>
            Cart ({cartList.length})
          </Button>
        </Grid>
        <Grid item md={4}>
          <Button
            onClick={() => setOpenCheckStatus(true)}
            className={classes.button}
          >
            Tracking Order
          </Button>
        </Grid>
      </Grid>

      <Grid container className={classes.wrapper} spacing={2}>
        {productList.map(row => {
          return (
            <Product
              product={row}
              handleAdd={handleAdd}
              role={'customer'}
            ></Product>
          )
        })}
      </Grid>
      <Cart
        open={open}
        setOpen={setOpen}
        cartList={cartList}
        handleIncrement={handleIncrement}
        setOpenCheckout={setOpenCheckout}
        handleRemove={handleRemove}
      />
      <CheckOut
        openCheckOut={openCheckout}
        setOpenCheckout={setOpenCheckout}
        cartList={cartList}
        setCartList={setCartList}
      />
      <CheckStatus
        openCheckStatus={openCheckStatus}
        setOpenCheckStatus={setOpenCheckStatus}
        productList={productList}
      />
      <Customize
        openCustomize={openCustomize}
        setOpenCustomize={setOpenCustomize}
        handleAdd={handleAdd}
      />
    </>
  )
}
export { Shop }
