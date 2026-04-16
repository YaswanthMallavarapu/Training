import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'

const TiicketList = () => {
    const { status } = useParams()
    const [tickets, setTickets] = useState([])
    const [errMsg,setErrMsg]=useState(undefined)
    const navigate=useNavigate()

    const api = "http://localhost:8080/api/ticket/get-all/v2"
    const updateApi="http://localhost:8080/api/ticket/update/status/"
    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }
    }

    useEffect(() => {
        const fetchTickets = async () => {
            const response = await axios.get(api, config)
            setTickets(response.data)
            filter(response.data)

        }


        const filter = (tickets) => {

            const temp = tickets.filter(ticket => ticket.status === status)
            console.log(temp);
            setTickets([...temp])
        }
        fetchTickets()

    }, [status])

    const closeTicket=async(ticketId)=>{

       try {
        const response= await axios.put(updateApi+`${ticketId}?ticketStatus=CLOSE`,{},config);
        navigate("/customer-dashboard/show-ticket/CLOSE")

        setErrMsg(undefined)
       } catch (error) {
        setErrMsg(error.message)
       }



    }



    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Subject</th>
                        <th scope="col">Status</th>
                        <th scope="col">Priority</th>
                        <th scope="col">Created Date</th>
                        <th scope="col">Executive Name</th>
                        <th scope="col">Action</th>

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
                                <td><button className="btn btn-warning" onClick={()=>closeTicket(ticket.id)}>Close Ticket</button></td>


                            </tr>
                            
                            ))
                    }


                </tbody>
            </table>
        </div>
    )
}

export default TiicketList
