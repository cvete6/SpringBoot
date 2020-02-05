import React from 'react';
import {Link, useHistory} from "react-router-dom";

const AddIngredient = (props)=>{

    const history = useHistory();

    const onFormSubmit = (e) => {
        e.preventDefault();
        console.log(e);

        props.onSubmit(
            {
                "name":e.target.name.value,
                "amount": e.target.amount.value,
                "veggie": e.target.veggie.checked,
                "spicy": e.target.spicy.checked
            }
        );
        history.push("/ingredients");
    }

    return(
        <div className="row">
            <form onSubmit={onFormSubmit} className="card">
                <h4 className="text-upper text-left">Add Ingredient</h4>
                <div className="form-group row">
                    <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Ingredient name</label>
                    <div className="col-sm-6">
                        <input name={"name"} type="text" className="form-control" id="ingredient"/>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="amount" className="col-sm-4 offset-sm-1 text-left">Amount</label>
                    <div className="col-sm-6">
                        <input name={"amount"} type="text" className="form-control" id="amount"/>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">Veggie</label>
                    <div className="col-sm-6 col-xl-4">
                        <input name={"veggie"} type="checkbox" className="form-control" id="veggie"/>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">Spicy</label>
                    <div className="col-sm-6 col-xl-4">
                        <input name={"spicy"} type="checkbox" className="form-control" id="spicy" />
                    </div>
                </div>

                <div className="form-group row">
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            type="submit"
                            className="btn btn-primary text-upper">
                            Save
                        </button>
                    </div>
                    <div className="offset-sm-1 col-sm-3  text-center">
                        <Link to={"/"}>
                        <button
                            className="btn btn-danger text-upper">
                            Cancel
                        </button>
                        </Link>
                    </div>
                </div>
            </form>
        </div>
    )

}

export default AddIngredient;