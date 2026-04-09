import React, { useState } from 'react'

const ReadForm = () => {

    const [name,setName]=useState("")
    const [username,setUsername]=useState("")
    const [password,setPassword]=useState("")

    const handleSubmit=($event)=>{

        $event.preventDefault()
        console.log(name);
        console.log(username);
        console.log(password);
    }

  return (
    <div>
      <h1>Read Form</h1>
      <div>
        <form action="">
            <div>
                <label htmlFor="">Enter Name : </label>
                <input type="text" onChange={($event)=>setName($event.target.value)}/>
            </div>
            <div>
                <label htmlFor="">Enter Username : </label>
                <input type="text" onChange={($event)=>setUsername($event.target.value)}/>
            </div>
            <div>
                <label htmlFor="">Enter Password : </label>
                <input type="password" onChange={($event)=>setPassword($event.target.value)}/>
            </div>

            <button className="submit" type="submit" onClick={($event)=>handleSubmit($event)}>Submit</button>
        </form>
      </div>
    </div>
  )
}

export default ReadForm
