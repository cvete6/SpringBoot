import React from 'react';
import {Link} from "react-router-dom";

const Header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-dark">
                <a className="navbar-brand" href="#">Pizza store</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to={"/"}>Home</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/pizzas"}>Pizzas</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className={"nav-link"} to={"/ingredients"}>Ingredients</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}
export default Header;
