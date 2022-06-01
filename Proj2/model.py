from agents import Sheep 
from agents import Wolf 
from agents import Grass 
from mesa import Model
from mesa.time import RandomActivation
from mesa.space import MultiGrid
from mesa.datacollection import DataCollector


class Model(Model):
    """A model with some number of agents."""

    def __init__(self, S, W, G, width, height):
        number_of_agents=0

        self.num_sheep = S
        self.num_wolfs = W
        self.num_grass = G

        self.to_remove_sheep = []
        self.to_remove_wolfs = []
        
        self.grid = MultiGrid(width, height, True)
        self.schedule = RandomActivation(self)
        self.running = True
        data = {
                "Wolf": lambda m: m.num_wolfs,
                "Grass": lambda m: m.num_grass,
                "Sheep": lambda m: m.num_sheep,
                }
        
        
        self.datacollector = DataCollector(data)
        
        # Create agents
        for i in range(self.num_sheep):
            a = Sheep(number_of_agents, self)
            self.schedule.add(a)
            number_of_agents += 1
            # Add the agent to a random grid cell
            x = self.random.randrange(self.grid.width)
            y = self.random.randrange(self.grid.height)
            self.grid.place_agent(a, (x, y))
        for i in range(self.num_wolfs):
            a = Wolf(number_of_agents, self)
            self.schedule.add(a)
            number_of_agents += 1
            # Add the agent to a random grid cell
            x = self.random.randrange(self.grid.width)
            y = self.random.randrange(self.grid.height)
            self.grid.place_agent(a, (x, y))
        for i in range(self.num_grass):
            a = Grass(number_of_agents, self)
            self.schedule.add(a)
            number_of_agents += 1
            # Add the agent to a random grid cell
            x = self.random.randrange(self.grid.width)
            y = self.random.randrange(self.grid.height)
            self.grid.place_agent(a, (x, y))
        
   
    def step(self):
        self.datacollector.collect(self)
        nsheep=len(self.to_remove_sheep)
        nwolfs=len(self.to_remove_wolfs)
        for i in range(nsheep):
            self.grid.remove_agent(self.to_remove_sheep[i])
            self.schedule.remove(self.to_remove_sheep[i])
            self.num_sheep -= 1
        for i in range(nwolfs):
            self.grid.remove_agent(self.to_remove_wolfs[i])
            self.schedule.remove(self.to_remove_wolfs[i])
            self.num_wolfs -= 1
        self.to_remove_sheep.clear()
        self.to_remove_wolfs.clear()
        self.schedule.step()

        