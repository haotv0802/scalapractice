//package testing;
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.web.bind.annotation._;
//
//@RestController
//@RequestMapping(path = ("/api"))
//class UserController(@Autowired val userService:UserService,@Autowired val dataSource:DataSource){
//  @GetMapping(path = Array("/users"))
//  def getAllUsers():Iterable[Users]={
//    userService.listUsers
//  }
//
//  @GetMapping(path = "/users/{id}")
//  def getUser(@PathVariable id:Long):Users={
//    userService.getUser(id)
//  }
//
//  @PostMapping(path = ("/users"))
//  def createUser(@RequestBody users:Users):ResponseEntity[Long]={
//    val id=userService.createUser(users)
//    new ResponseEntity(id,new HttpHeaders,HttpStatus.CREATED)
//  }
//}