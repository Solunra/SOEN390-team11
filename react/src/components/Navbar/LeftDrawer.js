import React from 'react'
import {
  AppBar,
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Toolbar
} from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles'
import { useHistory } from 'react-router-dom'
import ExitToAppRoundedIcon from '@material-ui/icons/ExitToAppRounded'
import StorefrontRoundedIcon from '@material-ui/icons/StorefrontRounded'
import ListAltOutlinedIcon from '@material-ui/icons/ListAltOutlined'
import BuildOutlinedIcon from '@material-ui/icons/BuildOutlined'
import GrainIcon from '@material-ui/icons/Grain'
import BorderColorIcon from '@material-ui/icons/BorderColor'
import ViewHeadlineIcon from '@material-ui/icons/ViewHeadline'
import {
  AccountBalanceRounded,
  ListAltRounded, PersonAddSharp,
  SupervisedUserCircle,
} from '@material-ui/icons'
import PersonIcon from '@material-ui/icons/Person';

const useStyles = makeStyles({
  drawer: {
    width: '190px'
  }
})

const LeftDrawer = ({ role }) => {
  const history = useHistory()
  const logout = () => {
    localStorage.removeItem('Authorization')
    history.push('/')
  }
  const classes = useStyles()
  const handleClick = dir => {
    history.entries = []
    history.index = -1
    history.push('/' + role + '/' + dir)
  }
  let itemsList
  if (role === 'customer') {
    itemsList = [
      {
        text: 'Shop',
        icon: <StorefrontRoundedIcon />,
        onClick: () => {
          handleClick('shop')
        }
      },
      {
        text: 'Customer Order',
        icon: <ListAltRounded />,
        onClick: () => {
          handleClick('customerOrder')
        }
      },
      {
        text: 'info',
        icon: <PersonIcon />,
        onClick: () => {
          handleClick('info')
        }
      },
      {
        text: 'Logout',
        icon: <ExitToAppRoundedIcon />,
        onClick: () => logout()
      }
    ]
  } else if (role === 'admin') {
    itemsList = [
      {
        text: 'Production',
        icon: <BuildOutlinedIcon />,
        onClick: () => {
          handleClick('production')
        }
      },
      {
        text: 'Raw Material',
        icon: <GrainIcon />,
        onClick: () => {
          handleClick('rawMaterial')
        }
      },
      {
        text: 'Inventory',
        icon: <ListAltOutlinedIcon />,
        onClick: () => {
          handleClick('inventory')
        }
      },
      {
        text: 'Machinery',
        icon: <GrainIcon />,
        onClick: () => {
          handleClick('machinery')
        }
      },
      {
        text: 'Order',
        icon: <BorderColorIcon />,
        onClick: () => {
          handleClick('order')
        }
      },
      {
        text: 'Account',
        icon: <AccountBalanceRounded />,
        onClick: () => {
          handleClick('accounts')
        }
      },
      {
        text: 'Users',
        icon: <SupervisedUserCircle />,
        onClick: () => {
          handleClick('users')
        }
      },
      {
        text: 'Logs',
        icon: <ViewHeadlineIcon />,
        onClick: () => {
          handleClick('logs')
        }
      },
      {
        text: 'Logout',
        icon: <ExitToAppRoundedIcon />,
        onClick: () => logout()
      }
    ]
  }

  return (
    <div>
      <AppBar position='fixed'>
        <Toolbar style={{ color: 'black' }}></Toolbar>
      </AppBar>
      <Drawer variant='permanent' className={classes.drawer}>
        <List>
          {itemsList.map((item, index) => {
            const { text, icon, onClick } = item
            return (
              <ListItem button key={text} onClick={onClick}>
                {icon && <ListItemIcon>{icon}</ListItemIcon>}
                <ListItemText primary={text} />
              </ListItem>
            )
          })}
        </List>
      </Drawer>
    </div>
  )
}

export default LeftDrawer
