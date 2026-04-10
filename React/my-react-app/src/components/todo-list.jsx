import axios from 'axios'
import React, { useEffect, useState } from 'react'

const ToDoList = () => {

    const [todos, setTodos] = useState([])

    const [errMsg, setErrMsg] = useState(undefined)

    const apiUrl = "https://jsonplaceholder.typicode.com/todos"

    useEffect(() => {
        const getAllTodo = async () => {
            try {
                const response = await axios.get(apiUrl)
                setTodos(response.data)
                setErrMsg(undefined)
            } catch (error) {

                setErrMsg(error.message)
            }


        }
        getAllTodo()
    }, [])

    return (
        <div className="container mt-4">
            <h1>ToDo List</h1>
            {!(errMsg===undefined) &&
            (
                <div className='alert alert-danger'>
                    {errMsg}
                </div>
            )
        }
            <div className='row'>
                {
                    todos.map((todo, index) => (

                        <div className="col-lg-12" key={index}>
                            <div className="card mb-2">
                                <div className="card-header">
                                    {todo.title}
                                </div>
                                <div className="card-body">
                                    Completed : {todo.completed ? "YES" : "PENDING"}
                                </div>
                                <div className="card-footer">
                                    Action buttons here (Update) (Delete)
                                </div>
                            </div>
                        </div>
                    ))
                }


            </div>
        </div>
    )
}

export default ToDoList
