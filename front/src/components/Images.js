import React from "react"
import Image from "./Image"

class Images extends React.Component {
    render() {
        const filteredImages = this.props.images.filter(image => image.phototechId == this.props.phototech_id);
        if (this.props.images.length > 0)
            return (<div>
                {filteredImages.map((el => (
                    <Image phototech_id = {this.props.phototech_id} onEdit={this.props.onEdit} onDelete={this.props.onDelete} key={el.id} image={el}/>
                )))}
            </div>)
        else
        return (<div className="images">
            <h3>Картинок нет</h3>
        </div>)
    }
}
export default Images