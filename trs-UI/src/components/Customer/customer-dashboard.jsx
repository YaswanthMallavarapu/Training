import { useEffect, useState } from "react"
import Navbar from "./Navbar"
import axios from "axios"
import { Outlet, useNavigate } from "react-router-dom"
import Profile from "./profile"
import Stats from "./stat"

const CustomerDashboard = () => {

    const getApi = "http://localhost:8080/api/customer/get-one"
    const [customer, setCustomer] = useState(undefined)
    const navigate = useNavigate()
    useEffect(() => {
        const fetchCustomer = async () => {
            const config = {
                headers: {
                    "Authorization": 'Bearer ' + localStorage.getItem('token')
                }
            };
            try {
                const response = await axios.get(getApi, config);
                setCustomer(response.data)
            }
            catch (err) {
                navigate("/login")
            }
        }

        fetchCustomer();
    }, [])
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    <Navbar />
                </div>
            </div>
            <div className="row mt-4">
                <div className="col-sm-3">
                    <Profile />
                </div>
                <div className="col-md-9">
                    <Stats />
                </div>
            </div>
            <div>
                <Outlet/>
            </div>

        </div>

    )
}

export default CustomerDashboard