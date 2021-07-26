import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class LiveScoreInfo extends Component{


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
        const textStyle = {
            fontSize: 26,
            color: "#b80e0e",
            textAlign: "center",
            paddingTop: "100px",
            justifyContent:'center',
        }
        const {id} = this.props.user;
        return(
            <div>
                <div style={this.infoStyle()}>
                    <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                        <img src={this.props.user.strHomeTeamBadge} alt=""/>
                        <p>{this.props.user.strHomeTeam}</p>
                        <p>{this.props.user.intHomeScore}</p>
                    </div>
                    vs
                    <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                        <img src={this.props.user.strAwayTeamBadge} alt=""/>
                        <p>{this.props.user.strAwayTeam}</p>
                        <p>{this.props.user.intAwayScore}</p>
                    </div>
                </div>
                <p style={textStyle}>{this.props.user.strProgress}'</p>
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
LiveScoreInfo.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  LiveScoreInfo;