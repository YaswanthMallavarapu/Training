
import { ADD_TICKET, GET_ALL_TICKETS_BY_CUSTOMER_ID } from "../actions/ticketActions"
const initialState = {
    tickets: []
}

const ticketReducer = (state = initialState, action) => {

    switch (action.type) {
        case GET_ALL_TICKETS_BY_CUSTOMER_ID:
            return {
                ...state,
                tickets: action.payload
            }
        case ADD_TICKET:
            return {
                ...state,
                tickets:[...state.tickets,action.payload]
            }

        default:
            return state

    }


}

export default ticketReducer