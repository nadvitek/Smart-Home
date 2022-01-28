# Smart Home
<h1>Summary</h1>
This application represents a virtual house that is controlled by smart appliances.<p>
People and pets live in the house their normal live and they use the appliances or do some actions that appliances have to take care of.<p>
<h2>Configurations</h2>
There are 2 configurations that can be found in src/main/configs that creates our house as its written.<p>

Due to this configurations we create people, pets, rooms and appliances that are or live in the house.<p>
There are four types of people that can be found in the house - Children, Mom, Dad and Grandparents<p>
Children and their parents go to school/work everyday from Monday to Friday and Grandparents stay at home<p>
The life in the house starts on Monday as the day 1, we can change number of days for our application as we want (default one week)<p>
As the people and pets live in the house, they use appliances in the house<p>
<b>Examples for appliances</b><p>
<ul>
<li>When people enter a room, light sensors will active lights, when last person leaves they turn lights off</li>
<li>When they leave the house and it's empty, house automaticly locks itself</li>
<li>Lawnmower lawns the garden (takes time according to the area of garden) and fish tank filter cleans the tank every week</li>
<li>Blinds pull up every morning at 6:30 / 8:30 in the weekends and down every night</li>
<li>Vacuum cleans any room that gets dirty (dust sensors send signal whan that happens) on the floor it's on</li>
<li>The heating in rooms is on whenever somebody is inside, when room is empty, it turns off</li>
<li>People in the house use toilets and they cook 3 meals a day(when they are in work/school, they skip lunch)</li>
<li>When the day ends, they put some clothes in the wasching machine, when it's full, it sends signal to be turned on and adults will start it</li>
<li>In kitchen people use appliances like fridge, microwave or oven to prepare the meal, they fill dishwasher when they are done, which like washing machine sends signal when it's full</li>
</ul>
<b>
Some freetime activities for people
</b>
<ul>
<li>Riding a bike</li>
<li>Go skiing</li>
<li>Watching TV</li>
<li>Go to the gym</li>
<li>Watching the TV</li>
<li>Listening to music</li>
<li>Solving events(Coming soon)</li>
</ul>
Solving events means that they repair something or clean or they must do something with appliances<p>
<b>Used Design Patterns</b>
<ul>
<li>Singleton - the house is the only one (one instance of the class)</li>
<li>Iterator - when a person solves some Events, he gets EventIterator from list of waiting events that are made for him(children do something and adults something else)</li>
<li>Lazy Initialization - In the house, everyone has Bike and Ski, but there is little chance, that everyone will use it. So we create it only if the time for its use comes</li>
<li>Factory Method - EventCreator, ReportCreator</li>
<li>Observer - the pool or every room will be creating Events if they will be dirty enough to tell some people to clean them</li>
</ul>
