window.onload = async function () {
    var url = "/roomchat" + "/room";
    var response = await fetch(url, {method: "GET"});
    var body = await response.text();
    var rooms = JSON.parse(body);
    showAllRooms(rooms);
}

async function showAllRooms(rooms) {
    const roomList = document.getElementById('roomList');
    roomList.innerHTML = '';

    rooms.forEach(room => {
        const roomElement = document.createElement('div');
        roomElement.classList.add('room');

        const roomName = document.createElement('p');
        roomName.textContent = room.name;

        const roomInfo = document.createElement('p');
        roomInfo.textContent = `${room.currentMembers} / ${room.maxMembers}`;

        roomElement.appendChild(roomName);
        roomElement.appendChild(roomInfo);

        roomList.appendChild(roomElement);
    });
}


async function newroom() {
    var roomname=document.getElementById("chatName").value;
    var maxmember=document.getElementById("maxMembers").value;
    console.log(roomname+" "+maxmember);
    var url="/roomchat"+"/room"+`?roomname=${roomname}&maxmember=${maxmember}`;
    var response=await fetch(url,{method:"POST"});
    location.reload();
}

async function showCreateRoomPopup() {
    document.querySelector(".popup").style.display = "flex";
}

