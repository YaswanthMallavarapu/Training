import React, { useEffect, useState } from 'react'

const GetAllUsers = () => {


    const [users, setUsers] = useState([])
    const apiUrl = "https://jsonplaceholder.typicode.com/users"
    useEffect(() => {

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => setUsers(data))
    }, [])


    return (
        <div className='container mt-4'>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Username</th>
                        <th scope="col">City</th>
                        <th scope="col">Phone</th>

                        <th scope="col">Company</th>
                        <th scope="col">Website</th>

                    </tr>
                </thead>
                <tbody>

                    {
                        users.map((u, index) => (

                            <tr key={index}>
                                <th scope="row">{index+1}</th>
                                <td>{u.name}</td>
                                <td>{u.username}</td>
                                <td>{u.address.city}</td>
                                <td>{u.phone}</td>
                                <td>{u.company.name}</td>
                                <td>{u.website}</td>
                            </tr>

                        ))
                    }



                </tbody>
            </table>
        </div>
    )
}

export default GetAllUsers
