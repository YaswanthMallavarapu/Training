import axios from "axios"
import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"

const Login = () => {


    const [username, setUsername] = useState(undefined)
    const [password, setPassword] = useState(undefined)
    const [token, setToken] = useState(undefined)
    const [errMsg,setErrMsg]=useState(undefined)
    const navigate =useNavigate()
    const loginApi = "http://localhost:8080/api/auth/login"
    const detailsApi = "http://localhost:8080/api/auth/user-details"

    const processLogin = async (e) => {

        e.preventDefault()
       try {
         // console.log(username)
        // console.log(password)

        //creating encoded data as normal data will be rejected by spring-boot

        let encodedString = window.btoa(username + ":" + password)
        // console.log(encodedString)

        // setting up the configurations and adding headers 
        const config = {
            headers: {
                "Authorization": "Basic " + encodedString
            }
        }

        // actual api call

        const response = await axios.get(loginApi, config)

        // setting token to token seeter
        setToken(response.data.token)

        // storing token in the local storage as key value pair for easier access
        localStorage.setItem("token", response.data.token)



        // part 2 getting user-details from DB using role 

        const deatilsConfig = {
            headers: {
                "Authorization": "Bearer " + response.data.token
            }
        }

        const detailsResponse = await axios.get(detailsApi, deatilsConfig) 



        switch (detailsResponse.data.role) {
            case "CUSTOMER":
                navigate("/customer-dashboard")
                break;
            case "EXECUTIVE":
                navigate("/executive-dashboard")
                break;
            case "ADMIN":
                navigate("/admin-dashboard")
                break
        }
       } catch (error) {
         setErrMsg("Invalid Credentials")
       }

    }


    
    return (
        <div className="container">
            <div className="row mt-4">
                <div className="col-md-3"></div>
                <div className="col-md-6">
                    <div className="row">
                        <div className="col-lg-12 card">
                            <div className="card-header">
                                Login Form
                            </div>

                            <div className="card-body">
                                <form action="" onSubmit={(e) => processLogin(e)}>
                                    {
                                        !(errMsg==undefined)&&
                                        (
                                            <div className="alert alert-danger">
                                                {errMsg}
                                            </div>
                                        )
                                    }
                                    <div className="mt-3">
                                        <label htmlFor="">Username : </label>
                                        <input type="text" className="form-control" required="required"
                                            onChange={(e) => setUsername(e.target.value)} />
                                    </div>
                                    <div className="mt-3">
                                        <label htmlFor="">Password : </label>
                                        <input type="password" className="form-control" required="required"
                                            onChange={(e) => setPassword(e.target.value)} />
                                    </div>
                                    <div className="mt-3">
                                        <input type="submit" value="Login" />
                                    </div>
                                    <div className="mt-3">
                                        <p>Don't have an account? <Link to="/sign-up">Sign Up</Link></p>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-3"></div>
            </div>

        </div>
    )
}
export default Login