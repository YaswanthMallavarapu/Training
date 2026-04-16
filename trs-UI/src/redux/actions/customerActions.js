import axios from "axios"

export const GET_ALL_CUSTOMER='GET_ALL_CUSTOMER'

export const getAllcustomers=()=>{


    return async(dispatch)=>{
        const response=await axios.get("http://localhost:8080/api/customer/get-all",{
            headers:{
                Authorization:"Bearer "+localStorage.getItem("token")
            }
        })


        dispatch({
            type:GET_ALL_CUSTOMER,
            payload:response.data
        })

    }


}