import React, {Component} from 'react';
import MenuIcon from '@material-ui/icons/Menu';
import {Redirect} from "react-router-dom";


const header = {
    background:"#09b110",
    color: "#bd0d0d",
    padding:"10px",
    display:"flex",
    alignItems:'center',
    justifyContent:'space-between',

}

let check = false

class Header extends Component{

    state = {check : false, value:""}


    handleKeyPress = (e) => {
        if (e.key === 'Enter') {
        this.setState({check : true})
    }
    }

    render(){
        const BarStyling = {width:"20rem",background:"#F2F1F9", border:"none", padding:"0.5rem"};

        if (this.state.check){
            return <Redirect push to={{pathname: "/search",state:{SearchValue : this.state.value}}}/>
        }

        return(
            <header style={header}>
                <input
                    style={BarStyling}
                    key="random1"
                    // value=keyword
                    placeholder={"search..."}
                    onChange={(e) => this.setState({value:e.target.value})}
                    onKeyUp={this.handleKeyPress.bind(this)}
                />
                <h2>Sports Database</h2>
                <p></p>
            </header>
        );
    }
}

export default Header;