import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class TeamListInfo extends Component{

    state ={check : false};

    setCheck = () => {
        this.setState({check:true});
    }

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
            return <Redirect push to={{pathname: "/team",state: {TeamID : this.props.user.idTeam, TeamDescription : this.props.user.strDescriptionEN}}}/>
        }
        const {id} = this.props.user;
        return(
            <div style={this.infoStyle()}>
                <Button color="primary" variant="outlined" onClick={this.setCheck}>
                    {this.props.user.strTeam}
                </Button>
                <div className="team-badge" style={{ justifyContent:'center', alignItems:'center'}}>
                    <img src={this.props.user.strTeamLogo} alt=""/>
                </div>
            </div>
        );
    }
}


TeamListInfo.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  TeamListInfo;