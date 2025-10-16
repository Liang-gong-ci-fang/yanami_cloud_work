<template>
  <div class="character-showcase">
    <div class="title-section">
      <h1 class="main-title">败犬女主太多了</h1>
      <h2 class="subtitle">角色介绍</h2>
    </div>

    <div class="carousel-container">
      <div class="carousel-wrapper" :style="{ transform: `translateX(-${currentIndex * 100}%)` }">
        <div
          v-for="(character, index) in characters"
          :key="index"
          class="character-slide"
        >
          <div class="character-card">
            <div class="image-section">
              <img
                :src="character.image"
                :alt="character.name"
                class="character-image"
                @click="openMoegirlLink(character.link)"
              />
              <div class="image-overlay">
                <span class="click-hint">点击查看萌娘百科</span>
              </div>
            </div>

            <div class="info-section">
              <h3 class="character-name">{{ character.name }}</h3>
              <div class="character-description">
                <p v-for="(line, lineIndex) in character.description" :key="lineIndex">
                  {{ line }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="navigation">
        <button
          class="nav-button prev"
          @click="previousSlide"
          :disabled="currentIndex === 0"
        >
          ‹
        </button>

        <div class="dots-container">
          <span
            v-for="(character, index) in characters"
            :key="index"
            class="dot"
            :class="{ active: index === currentIndex }"
            @click="goToSlide(index)"
          >
          </span>
        </div>

        <button
          class="nav-button next"
          @click="nextSlide"
          :disabled="currentIndex === characters.length - 1"
        >
          ›
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const currentIndex = ref(0)
const characters = ref([])

const characterData = [
  {
    name: '八奈见杏菜',
    image: '/images/八奈见杏菜.png',
    link: 'https://zh.moegirl.org.cn/%E5%85%AB%E5%A5%88%E8%A7%81%E6%9D%8F%E8%8F%9C',
    textFile: '/txt/八奈见杏菜.txt'
  },
  {
    name: '小鞠知花',
    image: '/images/小鞠知花.jpg',
    link: 'https://zh.moegirl.org.cn/%E5%B0%8F%E9%9E%A0%E7%9F%A5%E8%8A%B1',
    textFile: '/txt/小鞠知花.txt'
  },
  {
    name: '温水佳树',
    image: '/images/温水佳树.jpg',
    link: 'https://zh.moegirl.org.cn/%E6%B8%A9%E6%B0%B4%E4%BD%B3%E6%A0%91',
    textFile: '/txt/温水佳树.txt'
  },
  {
    name: '马剃天爱星',
    image: '/images/马剃天爱星.jpg',
    link: 'https://zh.moegirl.org.cn/%E9%A9%AC%E5%89%83%E5%A4%A9%E7%88%B1%E6%98%9F',
    textFile: '/txt/马剃天爱星.txt'
  }
]

const loadCharacterDescription = async (textFile) => {
  try {
    const response = await fetch(textFile)
    const text = await response.text()
    return text.split('\n').filter(line => line.trim() !== '')
  } catch (error) {
    console.error('Error loading character description:', error)
    return ['角色介绍加载失败']
  }
}

const nextSlide = () => {
  if (currentIndex.value < characters.value.length - 1) {
    currentIndex.value++
  }
}

const previousSlide = () => {
  if (currentIndex.value > 0) {
    currentIndex.value--
  }
}

const goToSlide = (index) => {
  currentIndex.value = index
}

const openMoegirlLink = (link) => {
  window.open(link, '_blank')
}

onMounted(async () => {
  for (let data of characterData) {
    const description = await loadCharacterDescription(data.textFile)
    characters.value.push({
      ...data,
      description
    })
  }
})
</script>

<style scoped>
.character-showcase {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  background: url('/images/天台1.jpg') center/cover no-repeat;
  position: relative;
  overflow: hidden;
}

.character-showcase::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.1);
  z-index: 1;
}

.character-showcase > * {
  position: relative;
  z-index: 2;
}

.title-section {
  text-align: center;
  padding: 2rem 0;
  background: rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(15px);
  border-bottom: 1px solid rgba(255, 255, 255, 0.3);
}

.main-title {
  font-size: 2.8rem;
  color: #fff;
  margin-bottom: 0.5rem;
  text-shadow: 3px 3px 6px rgba(0, 0, 0, 0.5);
  font-weight: bold;
  letter-spacing: 0.05em;
}

.subtitle {
  font-size: 1.3rem;
  color: rgba(255, 255, 255, 0.95);
  font-weight: 400;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
}

.carousel-container {
  flex: 1;
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.carousel-wrapper {
  display: flex;
  height: 100%;
  transition: transform 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}

.character-slide {
  min-width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1.5rem 2rem;
}

.character-card {
  display: flex;
  width: 100%;
  max-width: 1400px;
  height: 65vh;
  background: rgba(255, 255, 255, 0.92);
  border-radius: 20px;
  box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.image-section {
  flex: 0 0 55%;
  position: relative;
  overflow: hidden;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.character-image {
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
  transition: transform 0.3s ease;
  max-width: 100%;
  max-height: 100%;
}

.image-section:hover .character-image {
  transform: scale(1.03);
}

.image-overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: linear-gradient(transparent, rgba(0, 0, 0, 0.8));
  color: white;
  padding: 1.5rem;
  transform: translateY(100%);
  transition: transform 0.3s ease;
}

.image-section:hover .image-overlay {
  transform: translateY(0);
}

.click-hint {
  font-size: 1rem;
  text-align: center;
  display: block;
  font-weight: 500;
}

.info-section {
  flex: 0 0 45%;
  padding: 3rem 2.5rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  overflow-y: auto;
}

.character-name {
  font-size: 2.8rem;
  color: #333;
  margin-bottom: 1.5rem;
  text-align: center;
  font-weight: bold;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
  letter-spacing: 0.02em;
}

.character-description {
  font-size: 1.15rem;
  line-height: 1.9;
  color: #555;
  text-align: left;
  max-height: 100%;
}

.character-description p {
  margin-bottom: 0.8rem;
  padding-left: 1.2rem;
  border-left: 4px solid #667eea;
  padding-top: 0.3rem;
  padding-bottom: 0.3rem;
}

.navigation {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1.5rem;
  background: rgba(255, 255, 255, 0.08);
  backdrop-filter: blur(15px);
  border-top: 1px solid rgba(255, 255, 255, 0.3);
}

.nav-button {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 2px solid rgba(255, 255, 255, 0.3);
  background: rgba(255, 255, 255, 0.95);
  color: #333;
  font-size: 1.8rem;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.nav-button:hover:not(:disabled) {
  background: #fff;
  transform: scale(1.1);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  border-color: rgba(255, 255, 255, 0.5);
}

.nav-button:disabled {
  opacity: 0.4;
  cursor: not-allowed;
  background: rgba(255, 255, 255, 0.6);
}

.dots-container {
  display: flex;
  gap: 1.2rem;
  margin: 0 2.5rem;
}

.dot {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.6);
  cursor: pointer;
  transition: all 0.3s ease;
  border: 1px solid rgba(255, 255, 255, 0.8);
}

.dot.active {
  background: #fff;
  transform: scale(1.4);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.dot:hover {
  background: rgba(255, 255, 255, 0.9);
  transform: scale(1.2);
}

@media (max-width: 768px) {
  .character-card {
    flex-direction: column;
    height: auto;
    max-height: 70vh;
  }

  .image-section {
    height: 300px;
  }

  .info-section {
    padding: 2rem;
  }

  .character-name {
    font-size: 2rem;
    margin-bottom: 1rem;
  }

  .main-title {
    font-size: 2rem;
  }
}
</style>