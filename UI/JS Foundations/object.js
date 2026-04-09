const user={
    id:1,
    name:"Yaswanth",
    skills:["Java","Python","C++","Spring Boot"]
}

console.log(user.name);

// Destructuring

const {id,name,skills}=user;

console.log(id);
console.log(name);
console.log(skills);

// iterating through array using forEach

skills.forEach(skill=>console.log(skill));