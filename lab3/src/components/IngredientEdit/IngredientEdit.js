import React,{useState,useEffect} from 'react'
import {useParams, useHistory, Link} from 'react-router-dom';
import axios from "../../custom-axios/axios"


const IngredientEdit = (props) =>{

    const [ingredient,setIngredient] = useState({});
    const [formValid,setFormValid]=useState(true);
    const [nameValid,setNameValid]=useState(true);
    const [amountValid,setAmountValid]=useState(true);
    const {ingredientId} = useParams();
    const history = useHistory();

    useEffect(() => {
        axios.get("/rest/ingredients/"+ingredientId).then((data)=>{
            setIngredient(data.data);
            console.log(data.data);
        })
    },[])

    const handleCheckboxChange  = (e) => {
        const paramName = e.target.name;
        const paramValue = e.target.checked;
        if(paramName=="veggie"){
            setIngredient({
                ...ingredient,
                veggie: paramValue
            });
        }
        else{
            setIngredient({
                ...ingredient,
                spicy: paramValue});
        }

    }

    const validate=(e)=>{
        const paramValue = e.target.value;
        const paramName=e.target.name;
        console.log(paramName);
        if(paramName=="name"){
            debugger;
            if(!paramValue || paramValue.toString().length>50 || paramValue===""){
               setNameValid(false);
               console.log("sd" + nameValid)
            }
            else{
                setNameValid(true)
            }
        }
        else{
            if(!paramValue || paramValue.toString().length>50){
                setAmountValid(false)
            }
            else setAmountValid(true)
        }
        console.log(nameValid+" "+amountValid);
        FormValidation();
    }

    const FormValidation=()=>{
        if(nameValid && amountValid) setFormValid(true)
        else setFormValid(false)
        console.log(formValid);
    }

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
                <h4 className="text-upper text-center">Edit Ingredient</h4>
                <div className="form-group row">
                    <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Ingredient name</label>
                    <div className="col-sm-6">
                        <input onChange={validate} name={"name"} type="text" className="form-control" id="ingredient" defaultValue={ingredient.name}/>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="amount" className="col-sm-4 offset-sm-1 text-left">Amount</label>
                    <div className="col-sm-6">
                        <input onChange={validate} name={"amount"} type="text" className="form-control" id="amount" defaultValue={ingredient.amount}/>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">Veggie</label>
                    <div className="col-sm-6 col-xl-4">
                        <input onChange={handleCheckboxChange} name={"veggie"} type="checkbox" className="form-control" id="veggie" checked={ingredient.veggie}/>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">Spicy</label>
                    <div className="col-sm-6 col-xl-4">
                        <input onChange={handleCheckboxChange} name={"spicy"} type="checkbox" className="form-control" id="spicy" checked={ingredient.spicy}/>
                    </div>
                </div>

                <div className="form-group row">
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            type="submit"
                            className="btn btn-primary text-upper" disabled={!nameValid || !amountValid}>
                            Save
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-warning text-upper">
                            Reset
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <Link to={"/ingredients"}>
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

export default IngredientEdit;