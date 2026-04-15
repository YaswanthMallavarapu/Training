import React from 'react'
import PlanDetails from './PlanDetails'
import PlanList from './Plan-list'

const PlanHome = () => {
  return (
    <div className='row'>
        <div className="col-md-4">
            <PlanDetails/>
        </div>
        <div className="col-md-8">
            <PlanList/>
        </div>
      
    </div>
  )
}

export default PlanHome
