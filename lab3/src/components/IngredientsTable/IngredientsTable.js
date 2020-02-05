import React, {Component} from 'react';
import SingleIngredient from "../SingleIngredient/SingleIngredient";
import {Link} from "react-router-dom";

class IngredientsTable extends Component{

    componentDidMount(){
        this.props.onPageClick();
    }

    render() {

        const singleIngredients = this.props.ingredients.map((ingredient,index)=>{
            return <SingleIngredient onDelete={this.props.onDelete}
                ingredientId={ingredient.name} ingredient={ingredient} key={index}/>
        })

        let ListSize=this.props.ingredients.length;

        const renderPage=()=> {
            if (ListSize != 0)
                return (
                    <div className="row">
                        <h4 className="text-upper text-left">Ingredients</h4>
                        <div className="table-responsive">
                            <table className="table tr-history table-striped small">
                                <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Amount</th>
                                    <th scope="col">Spicy</th>
                                    <th scope="col">Veggie</th>
                                    <th scope="col">Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                {singleIngredients}
                                </tbody>
                            </table>
                        </div>
                        <button className="btn btn-outline-secondary">
                            <Link to={"/ingredients/new"}><span><strong>Add new ingredient</strong></span></Link>
                        </button>
                    </div>
                )
            else return <div>No ingredients in the system.</div>
        }

        return (
            <div>
                {renderPage()}
            </div>
        )
    }
}

export default IngredientsTable;