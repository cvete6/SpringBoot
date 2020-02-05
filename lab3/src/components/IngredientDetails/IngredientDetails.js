import React,{useState,useEffect} from 'react'
import {useParams, useHistory, Link} from 'react-router-dom';
import axios from "../../custom-axios/axios"
import SingleIngredient from "../IngredientsTable/IngredientsTable";
import Pizza from "../Pizza/Pizza";

const IngredientDetails=(props)=>{

    const [ingredient,setIngredient] = useState({});
    const [pizzas,setPizzas] = useState([]);
    const {ingredientId} = useParams();

    useEffect(() => {
        axios.get("/rest/ingredients/"+ingredientId).then((data)=>{
            setIngredient(data.data);
            console.log(data.data);
        })
        axios.get("/rest/ingredients/"+ingredientId+"/pizzas").then((data)=>{
            setPizzas(data.data);
            console.log(data.data);
        })
    },[])

    const veggie=()=>{
        if(ingredient.veggie) return "true";
        else return "false";
    }

    const spicy=()=>{
        if(ingredient.spicy) return "true";
        else return "false";
    }

    const listPizzas=()=>{
        return pizzas.map((pizza,index)=>{
            return <Pizza pizza={pizza} key={index}/>
        })
    }

    return(
        <div className="container">
            <div className="col-md-12">
                <h1>Ingredient Details</h1>
                <hr/>
                <div>Name: {ingredient.name}</div>
                <div>Amount: {ingredient.amount}</div>
                <div>Veggie: {veggie()}</div>
                <div>Spicy: {spicy()}</div>
            </div>
            <div className="col-md-12">
                <h1>Pizzas with {ingredient.name}</h1>
                <hr/>
                {listPizzas()}
            </div>
        </div>
    )

}

export default IngredientDetails;