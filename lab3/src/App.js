import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Header from "./components/Header/Header"
import IngredientsTable from "./components/IngredientsTable/IngredientsTable"
import IngredientService from "./repository/axiosIngredientsRepository";
import IngredientEdit from "./components/IngredientEdit/IngredientEdit";
import IngredientDetails from "./components/IngredientDetails/IngredientDetails";
import AddIngredient from "./components/AddIngredient/AddIngredient";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      ingredients: [],
    }
  }

  loadIngredients=()=>{
      IngredientService.fetchIngredients().then((data)=>{
        this.setState({
            ingredients: data.data.content});
      })
      console.log(this.state.ingredients);
  }

  editIngredient = ((editedIngredient) => {
        IngredientService.updateIngredient(editedIngredient).then((response)=>{
            const newIngredient = response.data;
            this.setState((prevState) => {
                const newIngredientsRef = prevState.ingredients.filter((item)=>{
                    if (item.name===newIngredient.name) {
                        return newIngredient;
                    }
                    return item;
                })
                return {
                    "ingredients": newIngredientsRef
                }
            });
        });
    });

    deleteIngredient = (ingredientId) => {
        IngredientService.deleteIngredient(ingredientId).then((response)=>{
            this.setState((state) => {
                const ingredients = state.ingredients.filter((t) => {
                    return t.name !== ingredientId;
                });
                return {ingredients}
            })
        })
    }

    addIngredient=(newIngredient)=>{
        IngredientService.addIngredient(newIngredient).then((response)=>{
            const newTerm = response.data;
            this.setState((prevState) => {
                const newIngredientsRef = [...prevState.ingredients, newIngredient];
                return {
                    "ingredients":newIngredientsRef
                }
            });

        })
    }



    render(){
    const routing = (
        <Router>
          <Header/>
          <main role="main" className="mt-3">
            <div className="container">
              <Route path={"/"} exact>
              </Route>
              <Route path={"/pizzas"}>
              </Route>
              <Route path="/ingredients" exact render={()=>
                  <IngredientsTable onPageClick={this.loadIngredients} ingredients={this.state.ingredients} onDelete={this.deleteIngredient}/>
              }>
              </Route>
                <Route path="/ingredients/:ingredientId/edit" render={()=>
                    <IngredientEdit onSubmit={this.editIngredient}/>}>
                </Route>
                <Route path="/ingredients/:ingredientId/details" render={()=>
                    <IngredientDetails/>}>
                </Route>
                <Route path="/ingredients/new" render={()=>
                    <AddIngredient onSubmit={this.addIngredient}/>}>
                </Route>
                <Redirect to={"/"}/>
            </div>
          </main>
        </Router>
    )
    return (

        <div className="App">
          {routing}
        </div>
    );

  }
}

export default App;
