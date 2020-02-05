import React from 'react';
import {Link} from "react-router-dom";

const SingleIngredient = (props)=>{

    var spicy,veggie;
    if(props.ingredient.spicy) spicy="true";
    else spicy="false";
    if(props.ingredient.veggie) veggie="true";
    else veggie="false";

    return(
        <tr>
            <td scope="col">{props.ingredient.name}</td>
            <td scope="col">{props.ingredient.amount}</td>
            <td scope="col">{spicy}</td>
            <td scope="col">{veggie}</td>
            <td scope="col">
                <button className="btn btn-sm btn-secondary">
                    <span className="fa fa-edit"/>
                    <Link to={"/ingredients/"+props.ingredient.name+"/edit"}><span><strong>Edit</strong></span></Link>
                </button>
                <button className="btn btn-sm btn-outline-secondary ">
                    <span className="fa fa-remove"/>
                    <span onClick={()=>props.onDelete(props.ingredientId)}><strong>Remove</strong></span>
                </button>
                <button className="btn btn-sm btn-outline-dark">
                    <Link to={"/ingredients/"+props.ingredient.name+"/details"}><span><strong>Details</strong></span></Link>
                </button>
            </td>
        </tr>
    )
}

export default SingleIngredient;