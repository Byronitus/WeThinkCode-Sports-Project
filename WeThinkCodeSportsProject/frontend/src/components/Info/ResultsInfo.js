import React, {Component} from "react";
import {makeStyles} from "@material-ui/core/styles";
import {Redirect} from "react-router-dom";
import TableContainer from "@material-ui/core/TableContainer";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class ResultsInfo extends Component{



    state ={check : false, Description : "", Video : "" , sportType : "",
        Event : "", League : "", Round : "", Date : "", Venue : "", Country : "", Started : ""};



    // setCheck = () => {
    //     this.setState({check:true});
    // }



    render() {

        const classes = makeStyles({
            table: {
                minWidth: 650,
            },
        });


        if (this.state.check){
            return <Redirect push to={{pathname: "/Events",state: {Description : this.state.Description, Video : this.state.Video, sportType : this.state.sportType,
                    Event : this.state.Event, League : this.state.League, Round : this.state.Round,
                    Date : this.state.Date, Venue : this.state.Venue, Country : this.state.Country, Started : this.state.Started
                }}}/>        }

        if (this.props.user.length > 0) {
            return (
                <TableContainer component={Paper}>
                    <Table className={classes.table} aria-label="simple table" style={{background: "#808080"}}>
                        <TableHead>
                            <TableRow>
                                <TableCell>Name Of Event</TableCell>
                                <TableCell align="right">Date</TableCell>
                                <TableCell align="right">Round</TableCell>

                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.props.user.map((result) => (
                                <TableRow key={result.strEvent} style={{background: "#808080"}}>
                                    <TableCell component="th" scope="row">
                                        <Button color="primary" variant="outlined" onClick={() => {
                                            this.setState({
                                                check: true,
                                                Description: result.strDescriptionEN,
                                                Video: result.strVideo,
                                                sportType: result.strSport,
                                                Event: result.strFilename,
                                                League: result.strLeague,
                                                Round: result.intRound,
                                                Date: result.dateEvent,
                                                Venue: result.strVenue,
                                                Country: result.strCountry,
                                                Started: result.strStatus
                                            })
                                        }}>
                                            {result.strEvent}
                                        </Button>
                                        {/*{fixture.strHomeTeam} vs {fixture.strAwayTeam}*/}
                                    </TableCell>
                                    <TableCell align="right">{result.dateEvent}</TableCell>
                                    <TableCell align="right">{result.intRound}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            );
        }else{
            return (<div> </div>);
        }
    }

}


ResultsInfo.propTypes = {
    user : PropTypes.array
}
export  default  ResultsInfo;