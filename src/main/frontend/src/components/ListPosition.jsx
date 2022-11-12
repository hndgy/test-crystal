import React from 'react'

function ListPosition({loading, positions, handleDelete}) {
  return (
    <div>
        {
      (!loading &&
          positions.map(pos =>
            <p key={pos.id}>[id : {pos.id}] = longitude : <b>{pos.longitude}</b>, latitude : <b>{pos.latitude}</b> 
                <button onClick={() => handleDelete(pos.id)}>supprimer</button>
            </p>
          )
        )|| <p>Chargement...</p>
        }
    </div>
  )
}

export default ListPosition