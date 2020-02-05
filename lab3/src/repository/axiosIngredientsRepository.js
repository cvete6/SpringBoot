import axios from '../custom-axios/axios'
import qs from 'qs'

const IngredientsService = {
    fetchIngredients: ()=> {
        return axios.get("/rest/ingredients");
    },

    updateIngredient:(ingredient) => {
        const data = {
            ...ingredient
        }
        const ingredientId=ingredient.name;
        const formParams = qs.stringify(data);
        console.log(formParams);
        return axios.patch("/rest/ingredients/"+ingredientId,formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    addIngredient:(ingredient)=>{
        const data = {
            ...ingredient
        }
        const formParams = qs.stringify(data);
        return axios.post("/rest/ingredients/",formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
    },

    deleteIngredient: (ingredientId) => {
        return axios.delete(`/rest/ingredients/${ingredientId}`);
    },

}

export default IngredientsService;