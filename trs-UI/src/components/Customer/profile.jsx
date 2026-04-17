import axios from 'axios'
import React, { useState } from 'react'

const Profile = () => {

    const path="uploads/"
    const [profile,setProfile]=useState(undefined)
    const [file,setFile]=useState(undefined)
    const api="http://localhost:8080/api/document/upload"
    const [status,setStatus]=useState(undefined)
    const filters=[
        {
            name:"All",
            value:"ALL"
        },
        {
            name:"Open",
            value:"OPEN"
        },
        {
            name:"In Progress",
            value:"IN_PROGRESS"
        },
        {
            name:"Closed",
            value:"CLOSE"
        }

    ]

    const uploadProfile=async()=>{

        const formdata=new FormData()
        formdata.append("file",file)
        const response=await axios.post(api,formdata,{
            headers:{
                Authorization:"Bearer "+localStorage.getItem("token")
            }
        })

        const path="uploads/"
        setProfile(path+response.data.profileImage)
        setFile(undefined)
    }
    return (
        <div>
            <div className="card" style={{width: '18rem'}}>
                <div className="card-body">
                    <div className="row">
                        <div className="col-lg-12">
                            {
                                !(profile===undefined)&&<img src={profile} alt="" />
                            }
                            
                        </div>
                        <div className='row'>

                            
                           <div className='col'> <input type="file" name="" id="" onChange={(e)=>setFile(e.target.files[0])}/></div>
                           <div className="col"><button onClick={uploadProfile}>Upload Profile</button></div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="filtering">
                <select name="" id="" onChange={(e)=>
                    setStatus(e.target.value)
                }>
                    
                    {
                     filters.map((filter,index)=>(
                    <option value={filter.value}>{filter.name}</option>
                     ))
                    }
                </select>
            </div>
        </div>
    )
}

export default Profile
