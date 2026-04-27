import React, { useEffect, useState } from 'react'
import { Chart } from 'primereact/chart'
const StatBar = () => {

    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});


    useEffect(()=>{

        const data = {
            labels: ['OPEN','CLOSE','IN_PROCESS'],
            datasets: [
                {
                    label: 'Sales',
                    data: [5,2,4],
                    backgroundColor: [
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(153, 102, 255, 0.2)'
                      ],
                      borderColor: [
                        'rgb(255, 159, 64)',
                        'rgb(75, 192, 192)',
                        'rgb(54, 162, 235)',
                        'rgb(153, 102, 255)'
                      ],
                      borderWidth: 1
                }
            ]
        };
        const options = {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };

        setChartData(data);
        setChartOptions(options);
    },[])

  return (
    <div>
      <div className="row">
        <div className="col-md-6">
            <div className="card">
            <Chart type="bar" data={chartData} options={chartOptions} />
        </div>
        </div>
      </div>
    </div>
  )
}

export default StatBar
