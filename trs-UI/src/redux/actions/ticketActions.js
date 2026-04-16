import axios from "axios";

export const GET_ALL_TICKETS_BY_CUSTOMER_ID='GET_ALL_TICKETS_BY_CUSTOMER_ID';
export const ADD_TICKET='ADD_TICKET'

export const getTicketByCustomer=(customerId)=>{

    return async (dispatch)=>{

        const response = await axios.get(`http://localhost:8080/api/ticket/get-all/${customerId}`,{
            headers:{
                Authorization:'Bearer '+localStorage.getItem("token")
            }
        })

        dispatch({
            type:GET_ALL_TICKETS_BY_CUSTOMER_ID,
            payload:response.data
        })

    }


}

export const addTicket=(ticket)=>{
     
    return (dispatch)=>{
        
        axios.post("http://localhost:8080/api/ticket/add",ticket,{
            headers:{
                Authorization:"Bearer "+localStorage.getItem("token")
            }
        })

        dispatch({
            type:ADD_TICKET,
            payload:ticket
        })

    }
}