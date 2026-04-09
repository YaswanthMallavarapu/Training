import React, { useState } from 'react'
import {ticket_data} from "../sample-data/ticket_data"
const TicketList = () => {

  const [tickets,setTickets]=useState(ticket_data)
  const [backupTickets,setBackupTickets]=useState(tickets)

  const HighPriorityTickets=()=>{
     const filtered=[...tickets].filter(ticket=>ticket.priority=="HIGH")
     setTickets(filtered)
  }
  const OpenTickets=()=>{
    const filtered=[...tickets].filter(ticket=>ticket.status=="OPEN")
     setTickets(filtered)
  }
  const ResetTickets=()=>{
     setTickets(backupTickets)
  }
     

  return (
    <div>

   <hr />
   <button onClick={()=>HighPriorityTickets()}>High Priority</button>
   <button onClick={()=>OpenTickets()}>OPEN</button>
   <button onClick={()=>ResetTickets()}>All </button>
   <hr />

      <h1>All Tickets</h1>
      {
        tickets.map((ticket,index)=>(
            <li key={ticket.id}>
                {(index+1) } . -- {ticket.subject} -- {ticket.priority} -- {ticket.status}
            </li>
        ))
      }
    
    </div>
  )
}

export default TicketList
