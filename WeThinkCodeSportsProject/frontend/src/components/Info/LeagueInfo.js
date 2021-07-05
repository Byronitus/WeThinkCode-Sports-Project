import React, {Component} from 'react';
import PropTypes from "prop-types";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import {Redirect} from "react-router-dom";
import Header from "../layouts/Header";

class LeagueInfo extends Component{

    state ={check : false};

    setCheck = () => {
        this.setState({check:true});
    }

    //dynamic Styling
    infoStyle = () =>{
        return {
            backgroundColor: (this.props.user.id %2) == 0? '#c8d4f7cc' :'',
            padding:'10px',
            borderBottom: '1px #ccc dotted',
            display:'flex',
            alignItems:'center',
            justifyContent:'space-between',
            justifyItems: 'flex-start',
        }
    }

    render() {
        //Destructuring instead of using this.props.user.<variable> you can now use name | surname
        if (this.state.check){
            return <Redirect push to={{pathname: "/teams",state: {sportID : this.props.user.idLeague, LeagueDescription : this.props.user.strDescriptionEN}}}/>
        }
        const {id} = this.props.user;
        return(
            <div style={this.infoStyle()}>
                <Button color="primary" variant="outlined" onClick={this.setCheck}>
                    {this.props.user.strLeague}
                </Button>
                <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                    <img src={this.props.user.strLogo} alt=""/>
                </div>
            </div>
        );
    }
}

const iconUsername = {
    display: 'flex',
    justifyContent:'space-between',

}

const buttons = {
    display: 'flex'
}
//PropTypes
LeagueInfo.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  LeagueInfo;