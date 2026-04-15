import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const Stats = () => {


    const [errMsg, setErrMsg] = useState(undefined)
    const [stats, setStats] = useState([])

    const api = "http://localhost:8080/api/ticket/stats/v2"
    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }
    }
    useEffect(() => {

        const getStats = async () => {

            try {
                const response = await axios.get(api, config)
                setStats(response.data)

            } catch (error) {
                setErrMsg("Something went wrong!")
            }

        }
        getStats()

    }, [])

    return (
        <div className='card '>
            <div className="row p-4">
                {
                    stats.map((stat, index) => (

                        <div className="col-sm-4" key={index + 1}>

                            <Link to={`/customer-dashboard/show-ticket/${stat.status}`}>
                                <div className="card">
                                    <div className="card-body ">
                                        <div className='text-center'>
                                            <h4>{stat.status}</h4>
                                            <h2>{stat.count}</h2>
                                        </div>

                                    </div>

                                </div>
                            </Link>
                        </div>
                    ))
                }

            </div>
        </div>
    )
}

export default Stats
