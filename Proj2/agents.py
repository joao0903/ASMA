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
        sameCell= self.model.grid.get_cell_list_contents([self.pos])
        for agent in sameCell:
            if(isinstance(agent, Grass)):
                self.model.grid.remove_agent(agent)
                self.model.schedule.remove(agent)
                self.model.num_grass -= 1
                self.energy += 2

    def step(self):
        self.eat()
        if self.energy > 0:
            self.lose_energy()
            self.move()
        else:
            
            self.model.to_remove_sheep.append(self)
            
            
            

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
            self.energy -= 1
    
    def eat(self):
        sameCell= self.model.grid.get_cell_list_contents([self.pos])
        for agent in sameCell:
            if(isinstance(agent, Sheep)):
                self.model.grid.remove_agent(agent)
                self.model.schedule.remove(agent)
                self.model.num_sheep -= 1
                self.energy += 2

 
        


    def step(self):
        self.move()
        self.eat()
        if self.energy > 0:
            self.lose_energy()
        else:

            self.model.to_remove_wolfs.append(self)
            
            



class Grass(Agent):
    

    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.energy = 10
        self.name= "Grass"

    def move(self):
        possible_steps = self.model.grid.get_neighborhood(
            self.pos, moore=True, include_center=False
        )

    def lose_energy(self):
            self.energy -= 1

    def step(self):
        self.move()
        
        if self.energy > 0:
            self.lose_energy()
    
