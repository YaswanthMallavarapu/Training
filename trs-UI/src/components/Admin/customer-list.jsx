import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getAllcustomers } from '../../redux/actions/customerActions'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'

const CustomerList = () => {

  const dispatch = useDispatch()
  const { customers } = useSelector(state => state.customerReducer)
const navigate=useNavigate()

  useEffect(() => {
    dispatch(getAllcustomers())
    
  }, [dispatch])
  return (
    <div>


      
      

      <div className="row">
        
        <table className="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">EMail</th>
              <th scope="col">City</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {
        customers.map((customer, index) => (

            <tr key={index}>
              <th scope="row">{index+1}</th>
              <td>{customer.name}</td>
              <td>{customer.email}</td>
              <td>{customer.city}</td>
              <td><button className='btn btn-warning'  onClick={()=>{
                navigate(`/show-customer-ticket/${customer.id}`)
              }}>Show Tickets</button></td>
            </tr>

            ))
          }
          </tbody>

        </table>
      </div>

        

      
    </div>
  )
}

export default CustomerList
