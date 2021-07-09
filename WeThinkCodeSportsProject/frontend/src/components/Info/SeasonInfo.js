import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class SeasonInfo extends Component{

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
        if (this.state.check){
            return <Redirect push to={{pathname: "/ListOfEvents",state: {Season : this.props.user.strSeason, LeagueId : this.props.LeagueId}}}/>
        }
        const {id} = this.props.user;
        return(
            <div style={this.infoStyle()}>
                <Button color="primary" variant="outlined" onClick={this.setCheck}>
                    {this.props.user.strSeason}
                </Button>
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
SeasonInfo.propTypes = {
    user : PropTypes.object.isRequired,
    LeagueId: PropTypes.any.isRequired
}
export  default  SeasonInfo;