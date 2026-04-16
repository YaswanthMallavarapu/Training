import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useParams } from 'react-router-dom'
import { getTicketByCustomer } from '../../redux/actions/ticketActions'
import axios from 'axios'

const CustomerTicket = () => {

    const { customerId } = useParams()
    const dispatch = useDispatch()
    const { tickets } = useSelector(state => state.ticketReducer)
    const [customer, setCustomer] = useState(undefined)

    useEffect(() => {

        dispatch(getTicketByCustomer(customerId))
        const fetchData = async () => {
            const response = await axios.get(`http://localhost:8080/api/customer/get/${customerId}`,{
                headers:{
                    Authorization:'Bearer '+localStorage.getItem("token")
                }
            })
            setCustomer(response.data)
            console.log(response.data);
        }
        fetchData()
    }, [dispatch,customerId])
    return (
        <div>

            <div className="row">
                <p>{customer?.name}--{customer?.email}--{customer?.city}</p>
            </div>
            <h2>Customer Tickets</h2>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Status</th>
                        <th scope="col">Priority</th>
                        <th scope="col">CreatedAt</th>
                        <th scope="col">CreatedAt</th>


                    </tr>
                </thead>
                <tbody>


                    {
                        tickets.map((ticket, index) => (

                            <tr key={index}>
                                <th scope="row">{index + 1}</th>
                                <td>{ticket.subject}</td>
                                <td>{ticket.status}</td>
                                <td>{ticket.priority}</td>
                                <td>{ticket.createdAt}</td>
                                <td>{ticket.executiveName}</td>


                            </tr>

                        ))
                    }
                </tbody>

            </table>


        </div>
    )
}

export default CustomerTicket
