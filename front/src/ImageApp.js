import React from "react"
import Header from "./components/Header"
import Images from "./components/Images"
import AddImage from "./components/AddImage"
import axios from "axios"
import { useParams } from "react-router-dom"

const baseUrl = "/images"

function withParams(Component) {
  return props => <Component {...props} params = {useParams()} />
}

class ImageApp extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
        images: []
    }
    
    var {id} = this.props.params;

    this.getImages = this.getImages.bind(this)
    this.addImage = this.addImage.bind(this)
    this.deleteImage = this.deleteImage.bind(this)
    this.editImage = this.editImage.bind(this)

    this.getImages()
}

  render() {
    return (<div>
        <Header title="Список фотографий" />
        <main>
          <Images phototech_id={this.props.params.id} images={this.state.images} onEdit={this.editImage} onDelete={this.deleteImage}/>
        </main>
        <aside>
          <AddImage phototech_id={this.props.params.id} onAdd={this.addImage}/>
        </aside>
      </div>)
  }

  getImages() {
      axios.get(baseUrl).then((res) => {
      this.setState({images: []}, ()=> {this.setState({images: res.data})})
    })
  }

  deleteImage(id) {
    axios.delete(baseUrl + "/" + id).then((res) => {
    this.getImages()
    console.log("Удаление\n" + res)
    })
  }

  editImage(image) {
      axios.put(baseUrl + "/" + image.id, image).then(res => {
      this.getImages()  
      console.log("Обновление\n" + res)
    })
  }
  
  addImage(image) {
      axios.post(baseUrl, image).then((res) => {
      console.log("Добавлени\n" + res)
      this.getImages()
    })
  }
}

export default withParams(ImageApp)