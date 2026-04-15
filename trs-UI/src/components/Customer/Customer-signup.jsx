import axios from "axios"
import { useState } from "react"
import { Link } from "react-router-dom"

const CustomerSignup = () => {

    const customerSignupUrl="http://localhost:8080/api/customer/sign-up"


    const [name, setName] = useState(undefined)
    const [city, setcity] = useState(undefined)
    const [email, setEmail] = useState(undefined)
    const [username, setisername] = useState(undefined)
    const [password, setPassword] = useState(undefined)
    const [successMsg,setSuccessMsg]=useState(undefined)
    const [errMsg,setErrMsg]=useState(undefined)

    const processSignUp=async (e)=>{

        e.preventDefault()
        try {
            await axios.post(customerSignupUrl,{
            name:name,
            city:city,
            email:email,
            username:username,
            password:password
        })
        setSuccessMsg("Customer signed up successfully!")
        setErrMsg(undefined)
        } catch (error) {
            setErrMsg(error.message)
            setSuccessMsg(undefined)
        }
    }


    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-md-3"></div>
                <div className="col-md-6">
                    <div className="row">
                        <div className="card">
                            <div className="card-header"> Customer Sign Up </div>
                            <form action="" onSubmit={(e) => processSignUp(e)}>

                                {
                                    !(successMsg==undefined)&&
                                    (
                                        <div className="alert alert-success mt-3">{successMsg}</div>
                                    )
                                }
                                {
                                    !(errMsg==undefined)&&
                                    (
                                        <div className="alert alert-danger mt-3">{errMsg}</div>
                                    )
                                }
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col-lg-12 mt-3">     
                                            <label htmlFor="">Name</label>
                                            <input type="text" required="required" className="form-control"
                                                onChange={(e) => setName(e.target.value)} />
                                        </div>
                                        <div className="col-lg-12 mt-3">
                                            <label htmlFor="">City</label>

                                            <input type="text" required="required" className="form-control"
                                                onChange={(e) => setcity(e.target.value)} />
                                        </div>
                                        <div className="col-lg-12 mt-3">
                                            <label htmlFor="">Email</label>
                                            <input type="email" required="required" className="form-control"
                                                onChange={(e) => setEmail(e.target.value)} />
                                        </div>
                                        <div className="col-lg-12 mt-3">
                                            <label htmlFor="">Username</label>
                                            <input type="text" required="required" className="form-control"
                                                onChange={(e) => setisername(e.target.value)} />
                                        </div>
                                        <div className="col-lg-12 mt-3">
                                            <label htmlFor="">Password</label>
                                            <input type="password" required="required" className="form-control"
                                                onChange={(e) => setPassword(e.target.value)} />
                                        </div>
                                        <div className="col-lg-12 mt-3">

                                            <input type="submit" value={"Sign Up"} />
                                        </div>
                                        <div className="col-lg-12 mt-3">

                                            <p>Already Have an account? <Link to="/login">Login</Link></p>
                                        </div>
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
export default CustomerSignup