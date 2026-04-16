import axios from "axios"
import { useState } from "react"
import { useDispatch } from "react-redux"
import { Link } from "react-router-dom"
import { addTicket } from "../../redux/actions/ticketActions"

const AddTicket = () => {




  const [subject, setSubject] = useState(undefined)
  const [details, setDetails] = useState(undefined)
  const [priority, setPriority] = useState(undefined)

  const [successMsg, setSuccessMsg] = useState(undefined)
  const [errMsg, setErrMsg] = useState(undefined)
  const dispatch = useDispatch()

  const TicketAdd = (e) => {

    e.preventDefault()
    try {
      dispatch(addTicket({
        subject: subject,
        details: details,
        priority: priority
      }))
      setSuccessMsg("Ticket added successfully!")
      setErrMsg(undefined)
    } catch (error) {
      setSuccessMsg(undefined)
      setErrMsg(error.message)
    }


  }


  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-md-3"></div>
        <div className="col-md-6">
          <div className="row">
            <div className="card">
              <div className="card-header"> Add Ticket </div>
              <form action="" onSubmit={(e) => processSignUp(e)}>

                {
                  !(successMsg == undefined) &&
                  (
                    <div className="alert alert-success mt-3">{successMsg}</div>
                  )
                }
                {
                  !(errMsg == undefined) &&
                  (
                    <div className="alert alert-danger mt-3">{errMsg}</div>
                  )
                }
                <div className="card-body">
                  <div className="row">
                    <div className="col-lg-12 mt-3">
                      <label htmlFor="">Subject: </label>
                      <input type="text" required="required" className="form-control"
                        onChange={(e) => setSubject(e.target.value)} />
                    </div>
                    <div className="col-lg-12 mt-3">
                      <label htmlFor="">Details</label>

                      <textarea type="text" required="required" className="form-control"
                        onChange={(e) => setDetails(e.target.value)} />
                    </div>

                  </div>
                  <div className="row mt-4">
                    <div className="col-lg-12">
                      <label htmlFor="">Priority: </label>
                      <select name="" id="" className="form-control col-lg-12" onChange={(e) => setPriority(e.target.value)}>
                        <option value="HIGH">HIGH</option>
                        <option value="MEDIUM">MEDIUM</option>
                        <option value="LOW">LOW</option>

                      </select>
                    </div>
                  </div>
                  <div className="mt-4">
                    <input type="submit" value="Add Ticket" onClick={(e) => TicketAdd(e)} />
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>

        <div className="col-md-3"></div>

      </div>

    </div>
  )
}
export default AddTicket