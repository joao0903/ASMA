from operator import ne
from mesa import Agent
import random

class Sheep(Agent):
    

    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.energy = 10
        self.name= "Sheep"

    def move(self):
        possible_steps = self.model.grid.get_neighborhood(
            self.pos, moore=True, include_center=False
        )
        new_position = self.random.choice(possible_steps)
        self.model.grid.move_agent(self, new_position)

    def lose_energy(self):
            self.energy -= 1
    
    def eat(self):
        neig = self.model.grid.get_neighbors(self.pos, True, False)
        sameCell= self.model.grid.get_cell_list_contents([self.pos])
        for agent in neig:
            if(isinstance(agent, Grass)):
                self.model.grid.remove_agent(agent)
                self.model.schedule.remove(agent)
                self.model.num_grass -= 1
                self.energy += 7

    def reproduce(self):
        rand = random.randint(0, 100)
        if(rand>=95):
            s = Sheep(self.model.number_of_agents, self.model)
            s.energy= self.energy/2
            self.energy= s.energy
            self.model.schedule.add(s)
            self.model.number_of_agents += 1
            self.model.num_sheep += 1
            x = self.model.random.randrange(self.model.grid.width)
            y = self.model.random.randrange(self.model.grid.height)
            self.model.grid.place_agent(s,(x,y))


    def step(self):
        self.move()
        self.eat()
        self.reproduce()
        
        if self.energy > 0:
            self.lose_energy()
            
        else:
            self.model.grid.remove_agent(self)
            self.model.schedule.remove(self)
            self.model.num_sheep -= 1
        
            
            

class Wolf(Agent):
    

    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.energy = 10
        self.name= "Wolf"

    def move(self):
        possible_steps = self.model.grid.get_neighborhood(
            self.pos, moore=True, include_center=False
        )
        new_position = self.random.choice(possible_steps)
        self.model.grid.move_agent(self, new_position)

    def lose_energy(self):
            self.energy -= 2
    
    def eat(self):
        neig = self.model.grid.get_neighbors(self.pos, True, False)
        sameCell= self.model.grid.get_cell_list_contents([self.pos])
        for agent in neig:
            if(isinstance(agent, Sheep)):
                self.model.grid.remove_agent(agent)
                self.model.schedule.remove(agent)
                self.model.num_sheep -= 1
                self.energy += 20                   

                

    def reproduce(self):
        rand = random.randint(0, 100)
        if(rand>=97):
            w = Wolf(self.model.number_of_agents, self.model)
            w.energy= self.energy/2
            self.energy= w.energy
            self.model.schedule.add(w)
            self.model.number_of_agents += 1
            self.model.num_wolfs += 1
            x = self.model.random.randrange(self.model.grid.width)
            y = self.model.random.randrange(self.model.grid.height)
            self.model.grid.place_agent(w,(x,y))
        


    def step(self):
        self.move()
        self.eat()
        self.reproduce()
        print(self.energy)
        if self.energy > 0:
            self.lose_energy()
        else:
            self.model.grid.remove_agent(self)
            self.model.schedule.remove(self)
            self.model.num_wolfs -= 1
            
            



class Grass(Agent):
    

    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.energy = 10

        self.name= "Grass"

    def move(self):
        possible_steps = self.model.grid.get_neighborhood(
            self.pos, moore=True, include_center=False
        )
    
    def spawn(self):
        rand = random.randint(0, 10)
        if(rand>=7):
            g = Grass(self.model.number_of_agents, self.model)
            self.model.schedule.add(g)
            self.model.number_of_agents += 1
            self.model.num_grass += 1
            x = self.model.random.randrange(self.model.grid.width)
            y = self.model.random.randrange(self.model.grid.height)
            self.model.grid.place_agent(g,(x,y))
    
    def step(self):
        self.move()
        #self.spawn()
        
