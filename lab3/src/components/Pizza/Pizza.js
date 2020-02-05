import React from 'react'

const Pizza =(props)=>{

    return(
        <div>
            <div>Pizza name: {props.pizza.name}</div>
            <div>Pizza description: {props.pizza.description}</div>
        </div>
    )
}

export default Pizza;