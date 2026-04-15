
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import AdminDashboard from './components/Admin/admin-dashboard.jsx'
import CustomerDashboard from './components/Customer/customer-dashboard.jsx'
import ExecutiveDashboard from './components/Executive/executive-dashboard.jsx'
import Login from './components/Auth/login.jsx'
import CustomerSignup from './components/Customer/Customer-signup.jsx'
import PlanHome from './components/Customer/PlanHome.jsx'
import TiicketList from './components/Customer/ticket-list.jsx'


const routes=createBrowserRouter([
    {
        path:"",
        element:<App/>
    },
    {
        path:"/login",
        element:<Login/>
    },
    {
        path:"/sign-up",
        element:<CustomerSignup/>
    },
    {
        path:"/admin-dashboard",
        element:<AdminDashboard/>
    },
    {
        path:"/customer-dashboard",
        element:<CustomerDashboard/>,
        children:[
            {
                index:true,
                element:<PlanHome/>
            },
            {
                path:"show-ticket/:status",
                element:<TiicketList/>
            }

        ]

    },
    {
        path:"/executive-dashboard",
        element:<ExecutiveDashboard/>
    }
])

createRoot(document.getElementById('root')).render(
  <RouterProvider router={routes}>
    <App />
    </RouterProvider>
  
)
