import axios from 'axios'
import React, { useState } from 'react'

const PostApi = () => {

    const [title, setTitle] = useState("")
    const [body, setBody] = useState("")
    const [userId, setUserId] = useState(undefined)
    const [sucMsg,setSucMsg]=useState(undefined)
    const [errMsg,setErrMsg]=useState(undefined)

    const api = "https://jsonplaceholder.typicode.com/posts"
    const insertPost = async(e) => {

        e.preventDefault()
        // console.log(title);
        // console.log(body);
        // console.log(userId);

        try {
            const response= await axios.post(api, {
            title: title,
            body: body,
            userId: userId,
        })
        setSucMsg("post inserted successfully")
        } catch (error) {
            setErrMsg(error.message)
        }

    }

    return (
        <div className="container">
            <div className='row mt-4'>
                <form action="" method="post" onSubmit={(e) => insertPost(e)}>
                    <div className="row">
                        <div className="col-md-12 ">
                            <div className="card">

                                <div className="card-header">
                                    Post Deatils
                                </div>
                                
                                    {
                                        !(sucMsg==undefined)&&
                                        (
                                             <div className="row mt-3">
                                            <div className="col-lg-12">
                                                <div className="alert alert-danger">
                                                    {sucMsg}
                                                </div>
                                            </div>
                                        </div>
                                        )
                                    }
                                
                                    {
                                        !(errMsg==undefined)&&
                                        (
                                             <div className="row mt-3">
                                            <div className="col-lg-12">
                                                <div className="alert alert-danger">
                                                    {errMsg}
                                                </div>
                                            </div>
                                        </div>
                                        )
                                    }
                                
                                <div className="card-body">
                                    <div className="row mb-3">
                                        <div className="col-md-3">
                                            <label htmlFor="">Enter post Title : </label>
                                        </div>
                                        <div className="col-md-9">
                                            <input type="text" className='form-control'
                                                onChange={(e) => setTitle(e.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-md-3">
                                            <label htmlFor="">Enter UserId : </label>
                                        </div>
                                        <div className="col-md-9">
                                            <input type="number" className='form-control' onChange={(e) => setUserId(e.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-md-3">
                                            <label htmlFor="">Enter post body : </label>
                                        </div>
                                        <div className="col-md-9">
                                            <textarea name="" id="" className='form-control'
                                                onChange={(e) => setBody(e.target.value)}></textarea>
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <div className="col-md-2">
                                            <input type="submit" value="Post" className='btn btn-primary form-control' />
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default PostApi
