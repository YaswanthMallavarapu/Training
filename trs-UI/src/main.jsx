
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
import { Provider } from 'react-redux'
import { store } from './store.js'
import CustomerList from './components/Admin/customer-list.jsx'
import CustomerTicket from './components/Admin/customer-tickets.jsx'
import AddTicket from './components/Customer/add-ticket.jsx'


const routes = createBrowserRouter([
    {
        path: "",
        element: <App />
    },
    {
        path: "/login",
        element: <Login />
    },
    {
        path: "/add-ticket",
        element: <AddTicket/>
    },
    {
        path: "/sign-up",
        element: <CustomerSignup />
    },
    {
        path: "/admin-dashboard",
        element: <AdminDashboard />,
        children: [
            {
                index: true,
                element: <CustomerList />
            }
        ]
    },
    {
        path: "/customer-dashboard",
        element: <CustomerDashboard />,
        children: [
            {
                index: true,
                element: <PlanHome />
            },
            {
                path: "show-ticket/:status",
                element: <TiicketList />
            },


        ]

    },
    {
        path: "/executive-dashboard",
        element: <ExecutiveDashboard />
    }
    ,
    {
        path: "/show-customer-ticket/:customerId",
        element: <CustomerTicket />
    }
])

createRoot(document.getElementById('root')).render(
    <Provider store={store}>
        <RouterProvider router={routes}>
            <App />
        </RouterProvider>
    </Provider>

)
