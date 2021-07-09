import React,{Component} from "react";
import PropTypes from "prop-types";
import ListOfPlayers from "./ListOfPlayers";

class MultipleLines extends Component{

    render(){
        return (this.props.text.split('\n').map((str => <p>{str}</p>)));
    }
}

MultipleLines.propTypes = {
    text:PropTypes.string
}

export default MultipleLines;