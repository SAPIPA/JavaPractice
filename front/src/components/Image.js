import React from "react"
import AddImage from "./AddImage"
import {IoCloseCircleSharp, IoHammerSharp} from 'react-icons/io5'

class Image extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            editForm: false
        }
    }
    image = this.props.image
    render() {
        return (
            <div className="images">
                <IoCloseCircleSharp onClick={() => this.props.onDelete(this.image.id)} className="delete-icon"/>
                <IoHammerSharp onClick={() => {
                    this.setState({
                        editForm: !this.state.editForm
                    })
                }} className="edit-icon" />
                /*<h3>{this.props.phototech_id}</h3>
                <h3>{this.image.phototechId}</h3>*/
                <h3>{this.image.title}</h3>
                <h3>{this.image.path_of_the_samurai}</h3>    
                {this.state.editForm && <AddImage phototech_id = {this.props.phototech_id} image={this.image} onAdd={this.props.onEdit} />}
            </div>
        )
    }
}

export default Image 