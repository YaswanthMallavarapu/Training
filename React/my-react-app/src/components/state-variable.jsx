import React, { useState } from 'react'

const StateVariable = () => {

    const [stateCount,setStateCount]=useState(0)
    let count=0
    const incrCount=()=>{
        count=count+1
        console.log(`incrCount called count : ${count}`);
    }

    const incrStateCount=()=>{
        setStateCount(stateCount+1)
        console.log(`incrStateCount called state count : ${stateCount}`);
    }

  return (
    <div>
      <button onClick={()=>incrCount()}>Increment Count</button> Count : {count} <br /><hr />
      <button onClick={()=>incrStateCount()}>INcrement StateCount</button> StateCount : {stateCount}
    </div>
  )
}

export default StateVariable


/**
 TWO Variables:
 1. Normal variable 
 2. State Variable  
  
 useState() is a React hook
 
 In Normal Variable, Even if the value of count changes, React does not re-render it, 
 so we don't see the latest value on screen

 In state variable, when the value gets updated, React re-renders that value and so we
 see the updated value instantly on screen 

 Hence as devs, we must always use state variables. 

 */