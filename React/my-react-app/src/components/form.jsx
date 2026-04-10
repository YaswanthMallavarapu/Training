import React, { useState } from 'react'

const Form = () => {


 const [formData,setFormData]=useState({
    name:"",
    mobile:"",
    router:"",
    app:[],
    password:"",
    fileName:""
 })


 const handleSubmit=($event)=>{

    $event.preventDefault()
    console.log(formData)
 }

 const handleCheckbox=($event)=>{
    const {value,checked}=$event.target

    setFormData(formData=>({
        ...formData,
        app:checked
        ?[...formData.app,value]
        :formData.app.filter(a=>a!=value)

    }))
 }


  return (
    <div>
      <div className="row mt-5">
        <div className="col-md-3"></div>
        <div className="col-md-6">
            <div className="card-header">Please fill the form</div>
            <div className="card-body">
                            <div className="row">
                                <div className="col-md-3 mb-4">
                                    <label>Name: </label>
                                </div>
                                <div className="col-md-9">
                                    <input type="text" className="form-control" onChange={($event)=>{setFormData({...formData,name:$event.target.value})}}/>
                                </div>
                            </div>
                            <div className="row mb-4">
                                <div className="col-md-3">
                                    <label>Mobile: </label>
                                </div>
                                <div className="col-md-9">
                                    <input type="number" className="form-control" onChange={($event)=>{setFormData({...formData,mobile:$event.target.value})}} />
                                </div>
                            </div>
                            <div className="row mb-4">
                                <div className="col-md-6">
                                    <label>Are you using router?: </label>
                                </div>
                                <div className="col-md-6">
                                    <input type="radio" value="yes" name="router" onChange={($event)=>{setFormData({...formData,router:$event.target.value})}}/>
                                    <label> Yes</label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" value="no" name="router" onChange={($event)=>{setFormData({...formData,router:$event.target.value})}}/>
                                    <label> No</label>
                                </div>
                            </div>
                            <div className="row mb-4">
                                <div className="col-md-7">
                                    <label>Are you using following Apps?: </label>
                                </div>
                                <div className="col-md-5">
                                    <input type="checkbox" value="teams" name="app" onChange={($event)=>handleCheckbox(event)}
                                   />
                                    <label> Teams</label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="checkbox" value="meet" name="app" onChange={($event)=>handleCheckbox(event)} 
                                    />
                                    <label> Meet</label>
                                </div>
                            </div>
                            <div className="row mb-4">
 
                                <div className="col-sm-3">
                                    <label>Password: </label>
                                </div>
                                <div className="col-md-9">
                                    <input type="password" className="form-control" onChange={($event)=>{setFormData({...formData,password:$event.target.value})}}/>
                                </div>
 
                            </div>
                            <div className="row ">
 
                                <div className="col-sm-4">
                                    <label>Upload your ID: </label>
                                </div>
                                <div className="col-md-8">
                                    <input type="file" onChange={($event)=>{setFormData({...formData,fileName:$event.target.files[0].name})}}/>
                                    <button className="btn btn-info mt-2" disabled>Upload ID</button>
                                </div>
 
                            </div>
                        </div>
                        <div className="card-footer"> 
                            <button onClick={($event)=>handleSubmit($event)}>Submit</button>
                        </div>
        </div>
        <div className="col-md-3"></div>
      </div>
    </div>
  )
}


export default Form