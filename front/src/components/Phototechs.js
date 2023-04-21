import React from "react"
import Phototech from "./Phototech"

class Phototechs extends React.Component {
    render() {
        if (this.props.phototechs.length > 0)
            return (<div>
                {this.props.phototechs.map((el => (
                    <Phototech onEdit={this.props.onEdit} onDelete={this.props.onDelete} key={el.id} phototech={el}/>
                )))}
            </div>)
        else
        return (<div className="phototechs">
            <h3>Фототехники нет</h3>
        </div>)
    }
}

export default Phototechs