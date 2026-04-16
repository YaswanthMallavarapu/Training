import { GET_ALL_CUSTOMER } from "../actions/customerActions"

const initalState = {

    customers:[]
}

const CustomerReducer = (state = initalState, action) => {

    switch (action.type) {
        case GET_ALL_CUSTOMER:
            return {

                ...state,
                customers:action.payload
            }
        default:
            return state
    }
}

export default CustomerReducer