import { useEffect, useState } from "react";
import CalculDistanceForm from "./components/CalculDistanceForm";
import CreateForm from "./components/CreateForm";
import ListPosition from "./components/ListPosition";
import PositionService from "./services/PositionService";

function App() {
  const [positions, setPositions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    PositionService.getAll().then(res => res.json())
      .then(data => {
        setPositions(data);
        setLoading(false);
      })
  },[])

  const handleDelete = (id) => {
    PositionService.delete(id).then(rep => {
      setLoading(true);

      if(rep.status === 204){//No content status
        var copy = [...positions];
        copy = copy.filter(x => x.id !== id);
        setPositions(copy);
      }else{
        alert("Impossible de supprimer la position")
      }

      setLoading(false);
    })
  }

  const handleCreate = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    PositionService.create(
      formData.get("longitude"),
      formData.get("latitude")
    ).then(resp => resp.json())
      .then(
        data => {
          setLoading(true);
          if(data.id){
            var copy = [...positions,data]
            setPositions(copy)
          }
          setLoading(false);
        }
      )
  }


  return (
    <div className="container">
      <CreateForm handleCreate={handleCreate} />
      <ListPosition handleDelete={handleDelete} positions={positions} loading={loading}/>
      <CalculDistanceForm loading={loading} positions={positions}/>
     
    </div>
  );
}

export default App;
